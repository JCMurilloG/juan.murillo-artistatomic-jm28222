package com.travelport.odbootcamp.artistatomic;

import java.util.Arrays;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelport.odbootcamp.mvsfacade.album.AlbumFacade;
import com.travelport.odt.restfw.plugin.common.annotations.OTMResource;
import com.travelport.odt.restfw.plugin.common.exceptions.ServiceException;
import com.travelport.odt.restfw.provider.mvs.MVSFacadeFactory;
import com.travelport.project.demonstration.AbstractAlbumResourceV1_0;
import com.travelport.project.demonstration.Album;
import com.travelport.project.demonstration.AlbumDetail;
import com.travelport.project.demonstration.AlbumID;
import com.travelport.project.demonstration.AlbumIdentifier;
import com.travelport.project.demonstration.AlbumListResponse;
import com.travelport.project.demonstration.DetailLevel;
import com.travelport.schema.common.Identifier;


/**
 * This class is the implementation of version 1.0 of the AlbumResource
 * that is defined in version 1.0 of the MP3-demo model.
 * <p>
 * This class demonstrates how to extend the GeneratorPlugin generated Abstract??Resource.
 * To be OD commpliant, resource implementations <b>MUST</b> extend the generated
 * Abstract class.
 * <p>
 * This class also demonstrates how to use the Facade pattern for model data access.
 * The purpose of the Facade pattern is to isolate model access to a single set of
 * classes, rather than being distributed throughout the service code and libraries.
 * When a model changes, and is adopted by the development team, the first place to
 * look is the Facade implementation for compile errors, and should be fixed there
 * first.  Then the code that uses the Facade classes can be modified if needed to
 * fix the compile errors if changes had to be made to the Facade.  The Facade implementation
 * in this project is in the AlbumFacade and AlbumHandler classes.
 * <p>
 * Additionally, this class demonstrates how substitution groups work, in the doCreate()
 * and the doQuery_Mp3() methods.  There is a video available for the ODBC-101 class that
 * explains in detail the concepts of the substitution group and how it is implemented in
 * OD service code.
 */
// TODO change the Path annotation to have your name.  The correct Path can be copied
// from the generated AbstractAlbumResourceV1_0 source code.
@Path("JM28222ç/Albums/v1_0")
@OTMResource(systemId = AbstractAlbumResourceV1_0.SYSTEM_ID,
    namespace = AbstractAlbumResourceV1_0.NAMESPACE, localName = "AlbumResource")
public class MyAlbumDomain extends AbstractAlbumResourceV1_0 {

  public static final Logger LOGGER = LoggerFactory.getLogger(MyAlbumDomain.class);

  /**
   * The CREATE action of the AlbumResource supports substitution group in the albumId parameter.
   * The doCreate() method returns the same detail level that is provided.  And because it doesn't 
   * make sense to create an Album from an AlbumID (which only contains identifier type data), 
   * the AlbumID object type is ignored and no creation will occur.
   * <p>
   * See the doQuery_Mp3() method below for an example of substituion group with a DetaiLevel response.
   *
   * @param albumId A substitution group parameter, which can be an AlbumID, Album, or AlbumDetail object.
   * @param version Ignored.  This is now deprecated with the Multi-Version Support of the model and framework.
   * @return The created Album object, at the same level of detail as was provided in the albumId parameter.  null
   *            is returned when the albumId parameter is an AlbumID object, or if the albumId provided is null.
   */
  @Override
  protected AlbumID doCreate(AlbumID albumId, String version) throws ServiceException {
    LOGGER.trace("doCreate()");

    // persist Album somewhere

    AlbumID createdAlbumId = null;

    if (albumId != null) {
      MVSFacadeFactory facadeFactory = MVSFacadeFactory.newInstance("1.0");
      AlbumFacade providedAlbumFacade = facadeFactory.getFacade(AlbumFacade.class);
      AlbumFacade createdAlbumFacade = facadeFactory.getFacade(AlbumFacade.class);

      if (albumId.getClass().equals(Album.class)) {
        providedAlbumFacade.set(albumId);

        String title = providedAlbumFacade.getTitle();

        Album createdAlbum = new Album();
        createdAlbumFacade.set(createdAlbum);

        createdAlbumFacade.setTitle(title);

        createdAlbumId = createdAlbum;
      } else if (albumId.getClass().equals(AlbumDetail.class)) {
        providedAlbumFacade.set(albumId);

        String title = providedAlbumFacade.getTitle();
        String label = providedAlbumFacade.getLabel();

        AlbumDetail createdAlbumDetail = new AlbumDetail();
        createdAlbumFacade.set(createdAlbumDetail);

        createdAlbumFacade.setTitle(title);
        createdAlbumFacade.setLabel(label);

        createdAlbumId = createdAlbumDetail;
      }

      createAlbumSomewhere(createdAlbumFacade);
    }

    return createdAlbumId;
  }

  /**
   * This method simulates persisting the album object somewhere. The system will provide the unique
   * GUID Identifier. The Identifier will be set in the provided object.
   * 
   * @param createdAlbumId The created album object
   */
  private void createAlbumSomewhere(AlbumFacade createdAlbumFacade) {
    // create the album in the source system
    // the source system provides the unique GUID Identifier
    Identifier id = new Identifier();
    id.setValue("123");

    createdAlbumFacade.setIdentifier(id);
  }

  /**
   * Deletes the Album data corresponding to the identifier parameter provided.
   *
   * @param identifier The identifier of the Album object to delete
   */
  @Override
  protected void doDelete(String identifier) throws ServiceException {
    LOGGER.trace("doDelete()");

    // get the Album object from somewhere
    // delete the Album object from persistent storage
  }

  /**
   * In reality the GET action in the AlbumResource should also use a substition group
   * for the level of detail to provide in the response.  But since this is used for
   * OD Bootcamp training, and substitution group is covered by two other actions in
   * the AlbumResource, we are keeping it simple by not implementing substution group
   * again in the GET action.
   *
   * @param identifier The Album identifier to retrieve from the AlbumResource
   * @return The AlbumIdentifier object corresponding to the identifier parameter.  
   *          See the ODBC-101 requirements for when null is returned.
   */
  @Override
  protected AlbumIdentifier doGet(String identifier) throws ServiceException {
    LOGGER.trace("doGet()");

    MVSFacadeFactory facadeFactory = MVSFacadeFactory.newInstance("1.0");
    AlbumFacade albumFacade = facadeFactory.getFacade(AlbumFacade.class);

    // get the Album from somewhere
    // the persisted Album will already have the identifier set
    Album album = new Album();

    albumFacade.set(album);

    // providing the non empty object for a workaround of a rest-framework bug
    AlbumIdentifier albumId = new AlbumIdentifier();
    Identifier id = new Identifier();
    id.setValue(identifier);
    albumId.setIdentifier(id);

    // use the handler to set or get anything on the Album object
    albumFacade.setIdentifier(albumId.getIdentifier());

    return albumId;
  }

  /**
   * The Query_Mp3 action of the AlbumResource uses the substitution group in the response, which
   * is a list of Album objects in the AlbumListResponse.  The AlbumListResponse is a list of AlbumID
   * objects, which can be an AlbumID, Album, or AlbumDetail.  The detailLevel parameter tells the
   * method implementation the level of detail to return in the response.
   *
   * @param song The song name to search for in the query
   * @param detailLevel The level of detail to return in the AlbumListResponse object
   * @param artistName The artist name to search for in the query
   * @return A list of AlbumID objects that match the query parameters provided, at the level of
   *          detail as specified in the detailLevel parameter
   */
  @Override
  protected AlbumListResponse doQuery_Mp3(String song, DetailLevel detailLevel, String artistName)
      throws ServiceException {
    LOGGER.trace("doQuery_Mp3()");

    MVSFacadeFactory facadeFactory = MVSFacadeFactory.newInstance("1.0");
    AlbumFacade albumFacade = facadeFactory.getFacade(AlbumFacade.class);

    // get the data from the source system that matches the query parameters
    AlbumID albumId = getAlbumDataFromSomewhere(detailLevel);

    // use the facade to get/set data in the album object
    albumFacade.set(albumId);

    Identifier identifier = albumFacade.getIdentifier();

    AlbumListResponse response = new AlbumListResponse();
    response.setAlbumID(Arrays.asList(albumId));

    return response;
  }

  /**
   * Simulates getting Album data from the source, and provides the correct detail level object per
   * the detailLevel parameter. If detailLevel is null, an AlbumID will be returned by default.
   * 
   * @param detailLevel
   * @return The AlbumID object at the level of detail as specified by the detailLevel parameter
   */
  private AlbumID getAlbumDataFromSomewhere(DetailLevel detailLevel) {
    AlbumID albumId;

    if (detailLevel != null) {
      // get the Album data from somewhere, per the query parameters
      switch (detailLevel) {
        // get AlbumDetail data from source
        case DETAIL:
          AlbumDetail albumDetail = new AlbumDetail();
          albumDetail.setTitle("Ten Summoners Tales");
          albumDetail.setLabel("A&M Records");
          albumId = albumDetail;
          break;

        // get Album (summary) data from source
        case SUMMARY:
          Album album = new Album();
          album.setTitle("Ten Summoners Tales");
          albumId = album;
          break;

        // get AlbumID data from source
        case ID:
        default:
          albumId = new AlbumID();
          break;
      }
    } else {
      albumId = new AlbumID();
    }

    Identifier id = new Identifier();
    id.setValue("123");

    albumId.setIdentifier(id);

    return albumId;
  }

}
