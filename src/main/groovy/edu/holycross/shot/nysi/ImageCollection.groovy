package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn
import edu.harvard.chs.cite.Cite2Urn


/** A class for configuring the CITE Image extension
 * for a single CITE Collections.
 */
class ImageCollection {


  /** URN for the Collection.*/
  Cite2Urn urn
  /** A human-readable label for the collection.*/
  String label
  /** Path to the file system root for pyramidal images.*/
  String path
  /** Name of the CITE Collection property to use for captions.*/
  String captionProperty
  /** Name of the CITE Collection property to use for rights statements.*/
  String rightsProperty


  /** Constructor made from an ordered list of configuration values.
   * @param config A five-element ArrayList defining the values
   * for all the members of an ImageCollection.
   * @throws Exception if config does not contain 5 elements, or
   * if the String value of the urn property does not form a valid
   * CITE URN.
   */
  ImageCollection(ArrayList config)
  throws Exception {
    if (config.size() != 5) {
      throw new Exception("ImageCollection: Cannot configure from a list of ${config.size()} elements: ${config}")
    }
    try {
			Cite2Urn urn
			if (config[0].contains(":cite:")){
				System.err.println(config[0])
				CiteUrn c1urn = new CiteUrn(config[0])
				System.err.println(c1urn)
				this.urn = new Cite2Urn(c1urn)
				System.err.println(urn)
			} else {
				System.err.println("Else: ${config[0]}")
	      this.urn = new Cite2Urn(config[0])
				System.err.println(urn)
			}
    } catch (Exception e) {
      throw new Exception ("ImageCollection: cannot form urn from ${config[0]}:  ${e}")
    }
    label = config[1]
    path = config[2]
    captionProperty = config[3]
    rightsProperty = config[4]
  }


}
