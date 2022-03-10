package com.travelport.odbootcamp.mvsfacade.album;

import java.util.List;

import com.travelport.odt.restfw.provider.mvs.MVSFacadeImpl;
import com.travelport.odt.restfw.provider.odFacade.ODHandlerBase;
import com.travelport.project.demonstration.Album;
import com.travelport.project.demonstration.AlbumDetail;
import com.travelport.project.demonstration.AlbumID;
import com.travelport.project.demonstration.ArtistID;
import com.travelport.project.demonstration.Song;
import com.travelport.schema.common.Identifier;

@MVSFacadeImpl(version = "1.0")
public class AlbumHandler extends ODHandlerBase<AlbumID> implements AlbumFacade {

  @Override
  public Identifier getIdentifier() {
    return hasObject() ? object.getIdentifier() : null;
  }

  @Override
  public void setIdentifier(Identifier identifier) {
    if (hasObject()) {
      object.setIdentifier(identifier);
    }
  }

  @Override
  public String getTitle() {
    return isAlbumObject() ? ((Album) object).getTitle() : null;
  }

  @Override
  public void setTitle(String title) {
    if (isAlbumObject()) {
      ((Album) object).setTitle(title);
    }
  }

  @Override
  public ArtistID getArtist() {
    return isAlbumObject() ? ((Album) object).getArtist() : null;
  }

  @Override
  public void setArtist(ArtistID artist) {
    if (isAlbumObject()) {
      ((Album) object).setArtist(artist);
    }
  }

  @Override
  public List<Song> getSongs() {
    return isAlbumObject() ? ((Album) object).getSong() : null;
  }

  @Override
  public void setSongs(List<Song> songs) {
    if (isAlbumObject()) {
      ((Album) object).setSong(songs);
    }
  }

  @Override
  public String getLabel() {
    return isAlbumDetailObject() ? ((AlbumDetail) object).getLabel() : null;
  }

  @Override
  public void setLabel(String label) {
    if (isAlbumDetailObject()) {
      ((AlbumDetail) object).setLabel(label);
    }
  }

  @Override
  public String getProducer() {
    return isAlbumDetailObject() ? ((AlbumDetail) object).getProducer() : null;
  }

  @Override
  public void setProducer(String producer) {
    if (isAlbumDetailObject()) {
      ((AlbumDetail) object).setProducer(producer);
    }
  }

  public boolean isAlbumObject() {
    return hasObject() && object instanceof Album;
  }

  public boolean isAlbumDetailObject() {
    return hasObject() && object instanceof AlbumDetail;
  }

}
