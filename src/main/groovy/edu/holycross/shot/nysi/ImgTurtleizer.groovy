package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn
import edu.harvard.chs.cite.Cite2Urn

/** Class for generating RDF statements for the HMT project's CITE Image extension.
 * The ImgTurtleizer uses csv or tsv files to create ImageCollection objects,
 * and can generate RDF statements for each Image Collection.
 */
class ImgTurtleizer {

  /** RDF prefix declarations. */
  static String prefix = "@prefix hmt:        <http://www.homermultitext.org/hmt/rdf/> .\n@prefix cite:        <http://www.homermultitext.org/cite/rdf/> .\n@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>. \n\n"

  /** A group of configurations for one or more Image Collection extensions. */
  //ImageService imgService =

  /** A directory with configuration files. */
  File sourceDir


  /** Constructor taking String name of a directory.
   * @param dirName Name of directory with Image Colleciton
   * configuration files.
   * @throws Exception if a file cannot be made from dirName.
   */
  ImgTurtleizer(String dirName)
  throws Exception {
    this.sourceDir = new File (dirName)
  }



  /** Creates a String composed of TTL statements documenting
   * all configured Image Collection extensions.
   * @param svc The ImageService to describe in RDF.
   * @returns A multi-line String of TTL.
   * @throws Exception if any lines of the configuration files
   * cannot be parsed.
   */
  String ttlizeCollections(ImageService svc)
  throws Exception {
    StringBuffer reply = new StringBuffer()
    svc.getCollectionConfigs().each { imgColl ->
      reply.append("<${imgColl.getUrn()}> rdf:type cite:ImageArchive .\n")
      //reply.append("<${urn}> rdf:label " + '"' + triple[1] + '" .\n')
      reply.append("<${imgColl.getUrn()}> hmt:path " + '"' + imgColl.getPath() + '" .\n')
      reply.append("<${imgColl.getUrn()}> hmt:imageCaptionProperty " + '"' + imgColl.getCaptionProperty() + '" .\n')
      reply.append("<${imgColl.getUrn()}> hmt:imageRightsProperty " + '"' + imgColl.getRightsProperty() + '" .\n')
    }
    return reply.toString()
  }


  /** Turtleize configuration of all Image Collection extensions,
   * and write resulting TTL, without RDF prefix statements, to outFile.
   * @param outFile File where TTL is written.
   */
  void ttl(File outFile)
  throws Exception {
    ttl(outFile, false)
  }


  /** Turtleize configuration of all Image Collection extensions,
   * and write resulting TTL, optionally including RDF prefix statements, to outFile.
   * @param outFile File where TTL is written.
   * @param includePrefix True if RDF prefix satements should be included.
   */
  void ttl(File outFile, boolean includePrefix)
  throws Exception {
    outFile.append(ttlize(includePrefix), "UTF-8")
  }


  /** Creates a String composed of RDF prefix and TTL statements documenting
   * all configured Image Collection extensions found in
   * this turtleizer's source directory.
   * @returns A multi-line String of TTL.
   * @throws Exception if any lines of the configuration files
   * cannot be parsed.
   */
  String ttlize()
  throws Exception {
    return ttlize(true)
  }

  /** Creates a String, optionally including RDF prefix statements,
   *  composed of TTL statements documenting
   * all configured Image Collection extensions found in
   * this turtleizer's source directory.
   * @param includePrefix True if RDF prefix statements should be
   * included.
   * @returns A multi-line String of TTL.
   * @throws Exception if any lines of the configuration files
   * cannot be parsed.
   */
  String ttlize(boolean includePrefix) {
    StringBuffer reply = new StringBuffer()
    if (includePrefix) {
      reply.append(prefix)
    }

    ImageService tsv = new TsvCollections(this.sourceDir)
    reply.append(ttlizeCollections(tsv))
    ImageService csv = new CsvCollections(this.sourceDir)
    reply.append(ttlizeCollections(csv))
    return reply.toString()
  }


}
