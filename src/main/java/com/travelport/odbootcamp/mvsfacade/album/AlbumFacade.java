package com.travelport.odbootcamp.mvsfacade.album;

import java.util.List;

import com.travelport.odt.restfw.provider.mvs.MVSFacadeIfc;
import com.travelport.odt.restfw.provider.odFacade.ODFacade;
import com.travelport.project.demonstration.AlbumID;
import com.travelport.project.demonstration.ArtistID;
import com.travelport.project.demonstration.Song;
import com.travelport.schema.common.Identifier;

@MVSFacadeIfc
public interface AlbumFacade extends ODFacade<AlbumID> {

  Identifier getIdentifier();

  void setIdentifier(Identifier identifier);

  String getTitle();

  void setTitle(String title);

  ArtistID getArtist();

  void setArtist(ArtistID artist);

  List<Song> getSongs();

  void setSongs(List<Song> songs);

  String getLabel();

  void setLabel(String label);

  String getProducer();

  void setProducer(String producer);

}
