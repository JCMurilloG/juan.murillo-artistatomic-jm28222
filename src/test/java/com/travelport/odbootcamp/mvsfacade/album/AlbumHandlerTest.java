package com.travelport.odbootcamp.mvsfacade.album;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.travelport.odt.restfw.provider.mvs.MVSFacadeFactory;
import com.travelport.project.demonstration.Album;
import com.travelport.project.demonstration.AlbumDetail;
import com.travelport.project.demonstration.AlbumID;
import com.travelport.project.demonstration.ArtistID;
import com.travelport.project.demonstration.DetailLevel;
import com.travelport.project.demonstration.Song;
import com.travelport.schema.common.Identifier;

public class AlbumHandlerTest {

  private AlbumHandler albumHandler;

  @Before
  public void setup() throws Exception {
    MVSFacadeFactory facadeFactory = MVSFacadeFactory.newInstance("1.0");
    AlbumFacade albumFacade = facadeFactory.getFacade(AlbumFacade.class);

    assertTrue(albumFacade instanceof AlbumHandler);

    albumHandler = (AlbumHandler) albumFacade;
  }

  @Test
  public void identifierTest() throws Exception {
    final String ID = "1234";
    Identifier identifier = new Identifier();
    identifier.setValue(ID);

    setAlbum(DetailLevel.ID);

    albumHandler.setIdentifier(identifier);

    Identifier facadeIdentifier = albumHandler.getIdentifier();

    assertNotNull(facadeIdentifier);
    assertEquals(identifier.getValue(), facadeIdentifier.getValue());
  }

  @Test
  public void titleTest() throws Exception {
    final String TITLE = "The Soul Cages";

    setAlbum(DetailLevel.SUMMARY);

    albumHandler.setTitle(TITLE);

    String facadeTitle = albumHandler.getTitle();

    assertNotNull(facadeTitle);
    assertEquals(TITLE, facadeTitle);
  }

  @Test
  public void artistTest() throws Exception {
    final String ID = "4567";
    ArtistID artistId = new ArtistID();
    Identifier identifier = new Identifier();
    identifier.setValue(ID);
    artistId.setIdentifier(identifier);

    setAlbum(DetailLevel.SUMMARY);

    albumHandler.setArtist(artistId);

    ArtistID facadeArtist = albumHandler.getArtist();

    assertNotNull(facadeArtist);
    assertEquals(artistId.getIdentifier().getValue(), facadeArtist.getIdentifier().getValue());
  }

  @Test
  public void songsTest() throws Exception {
    final String ID = "4567";
    Song song = new Song();

    List<Song> songs = new ArrayList<>();
    songs.add(song);

    setAlbum(DetailLevel.SUMMARY);

    albumHandler.setSongs(songs);

    List<Song> facadeSongs = albumHandler.getSongs();

    assertNotNull(facadeSongs);
    assertEquals(songs.size(), facadeSongs.size());
  }

  @Test
  public void labelTest() throws Exception {
    final String LABEL = "A&M Records";

    setAlbum(DetailLevel.DETAIL);

    albumHandler.setLabel(LABEL);

    String facadeLabel = albumHandler.getLabel();

    assertNotNull(facadeLabel);
    assertEquals(LABEL, facadeLabel);
  }

  @Test
  public void producerTest() throws Exception {
    final String PRODUCER = "Sting";

    setAlbum(DetailLevel.DETAIL);

    albumHandler.setProducer(PRODUCER);

    String producerLabel = albumHandler.getProducer();

    assertNotNull(producerLabel);
    assertEquals(PRODUCER, producerLabel);
  }

  /**
   * Tests the handler when the object is null.
   */
  @Test
  public void nullObjectTest() throws Exception {
    albumHandler.setIdentifier(new Identifier());
    assertNull(albumHandler.getIdentifier());

    albumHandler.setTitle("Ten Summoners Tales");
    assertNull(albumHandler.getTitle());

    albumHandler.setArtist(new ArtistID());
    assertNull(albumHandler.getArtist());

    albumHandler.setSongs(new ArrayList<>());
    assertNull(albumHandler.getSongs());

    albumHandler.setLabel("label");
    assertNull(albumHandler.getLabel());

    albumHandler.setProducer("producer");
    assertNull(albumHandler.getProducer());
  }

  /**
   * AlbumHandler not initialized, returns false
   * 
   * @throws Exception
   */
  @Test
  public void isAlbumObjectNotSetTest() throws Exception {
    assertFalse(albumHandler.isAlbumObject());
  }

  @Test
  public void isAlbumObjectDifferentTypeTest() throws Exception {
    setAlbum(DetailLevel.ID);
    assertFalse(albumHandler.isAlbumObject());
  }

  /**
   * AlbumHandler not initialized, returns false
   * 
   * @throws Exception
   */
  @Test
  public void isAlbumDetailObjectNotSetTest() throws Exception {
    assertFalse(albumHandler.isAlbumDetailObject());
  }

  @Test
  public void isAlbumDetailObjectDifferentTypeTest() throws Exception {
    setAlbum(DetailLevel.ID);
    assertFalse(albumHandler.isAlbumDetailObject());
  }

  private AlbumID setAlbum(DetailLevel detailLevel) throws Exception {
    AlbumID album = null;

    switch (detailLevel) {
      case ID:
        album = new AlbumID();
        break;

      case SUMMARY:
        album = new Album();
        break;

      case DETAIL:
        album = new AlbumDetail();
        break;
    }

    albumHandler.set(album);
    assertTrue(albumHandler.hasObject());

    album = albumHandler.get();
    assertNotNull(album);

    return album;
  }

}
