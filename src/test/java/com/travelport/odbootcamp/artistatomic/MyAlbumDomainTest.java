package com.travelport.odbootcamp.artistatomic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.travelport.project.demonstration.Album;
import com.travelport.project.demonstration.AlbumDetail;
import com.travelport.project.demonstration.AlbumID;
import com.travelport.project.demonstration.AlbumIdentifier;
import com.travelport.project.demonstration.AlbumListResponse;
import com.travelport.project.demonstration.DetailLevel;

/**
 * This JUnit test class, combined with the AlbumHandlerTest class, provides 
 * 100% code coverage of the classes that are provied by the archetype.
 * In Eclipse see the EclEmma code coverage report to confirm.
 * <p>
 * Therefore this test class also tests the substitution group scenarios for
 * the doCreate() and doQuery_Mp3() methods.
 */
public class MyAlbumDomainTest {

  private MyAlbumDomain albumDomain;

  @Before
  public void setup() {
    albumDomain = new MyAlbumDomain();
  }

  /**
   * Verifies that if an AlbumID object is provided to the doCreate() method
   * that null is returned, which means that an Album object was not created.
   */
  @Test
  public void doCreateWithIDTest() throws Exception {
    AlbumID albumID = albumDomain.doCreate(new AlbumID(), "0.0");

    assertNull(albumID);
  }

  @Test
  public void doCreateWithNullTest() throws Exception {
    AlbumID albumID = albumDomain.doCreate(null, "0.0");

    assertNull(albumID);
  }

  /**
   * Verifies that a summary level of AlbumID object will successfully
   * return the created Album object type, which is the SUMMARY level
   * of detail from the DetailLevel enumeration.
   */
  @Test
  public void doCreateWithSummaryTest() throws Exception {
    final String TITLE = "Ten Summoners Tales";
    Album album = new Album();
    album.setTitle(TITLE);

    AlbumID albumID = albumDomain.doCreate(album, "0.0");

    assertNotNull(albumID);
    assertEquals(Album.class, albumID.getClass());
    assertNotNull(albumID.getIdentifier());
    assertNotNull(albumID.getIdentifier().getValue());
    assertEquals(TITLE, ((Album) albumID).getTitle());
  }

  /**
   * Verifies that a detail level of AlbumID object will successfully
   * return the created AlbumDetail object type, which is the DETAIL level
   * of detail from the DetailLevel enumeration.
   */
  @Test
  public void doCreateWithDetailTest() throws Exception {
    final String TITLE = "Ten Summoners Tales";
    final String LABEL = "A&M Records";
    AlbumDetail albumDetail = new AlbumDetail();
    albumDetail.setTitle(TITLE);
    albumDetail.setLabel(LABEL);

    AlbumID albumID = albumDomain.doCreate(albumDetail, "0.0");

    assertNotNull(albumID);
    assertEquals(AlbumDetail.class, albumID.getClass());
    assertNotNull(albumID.getIdentifier());
    assertNotNull(albumID.getIdentifier().getValue());
    assertEquals(TITLE, ((AlbumDetail) albumID).getTitle());
    assertEquals(LABEL, ((AlbumDetail) albumID).getLabel());
  }

  @Test
  public void doDeleteTest() throws Exception {
    albumDomain.doDelete("1234");
  }

  @Test
  public void doGetTest() throws Exception {
    final String ID = "1234";

    AlbumIdentifier albumId = albumDomain.doGet(ID);

    assertNotNull(albumId);
    assertNotNull(albumId.getIdentifier());
    assertEquals(ID, albumId.getIdentifier().getValue());
  }

  @Test
  public void doQuery_Mp3NullDetailLevelTest() throws Exception {
    AlbumListResponse response = albumDomain.doQuery_Mp3("Fields of Gold", null, "Sting");

    assertNotNull(response);
    assertEquals(1, response.getAlbumID().size());
    assertEquals(AlbumID.class, response.getAlbumID().get(0).getClass());
  }

  /**
   * Verifies that the query response list contains AlbumID objects, 
   * per the DetailLevel.ID that is provided in the parameter.  AlbumID
   * is the ID level of detail.
   */
  @Test
  public void doQuery_Mp3IDTest() throws Exception {
    AlbumListResponse response = albumDomain.doQuery_Mp3("Fields of Gold", DetailLevel.ID, "Sting");

    assertNotNull(response);
    assertEquals(1, response.getAlbumID().size());
    assertEquals(AlbumID.class, response.getAlbumID().get(0).getClass());
  }

  /**
   * Verifies that the query response list contains Album objects, 
   * per the DetailLevel.SUMMARY that is provided in the parameter.  Album
   * is the SUMMARY level of detail.
   */
  @Test
  public void doQuery_Mp3SummaryTest() throws Exception {
    AlbumListResponse response =
        albumDomain.doQuery_Mp3("Fields of Gold", DetailLevel.SUMMARY, "Sting");

    assertNotNull(response);
    assertEquals(1, response.getAlbumID().size());
    assertEquals(Album.class, response.getAlbumID().get(0).getClass());
  }

  /**
   * Verifies that the query response list contains AlbumDetail objects, 
   * per the DetailLevel.DETAIL that is provided in the parameter.  Album
   * is the DETAIL level of detail.
   */
  @Test
  public void doQuery_Mp3DetailTest() throws Exception {
    AlbumListResponse response =
        albumDomain.doQuery_Mp3("Fields of Gold", DetailLevel.DETAIL, "Sting");

    assertNotNull(response);
    assertEquals(AlbumDetail.class, response.getAlbumID().get(0).getClass());
  }

}
