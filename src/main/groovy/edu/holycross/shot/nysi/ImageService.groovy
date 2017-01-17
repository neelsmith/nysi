package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn
import edu.harvard.chs.cite.Cite2Urn


/** An interface for configuring the CITE Image extension
 * for one or more CITE Collections.
 */
interface ImageService {

  /** Returns a five-element ArrayList defining the following values
   * for an Image Collection:
   * - CollectionURN
   * - Label
   * - Path to the file system root for pyramidal images
   * - CITE Collection property to use for captions
   * - CITE Collection property to use for rights
   */
  abstract ArrayList getCollectionConfigs()

}
