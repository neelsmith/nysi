package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn


/** Implementation of the ImageService interface
 * for collections documented in .tsv files.
 */
class TsvCollections implements ImageService {


  /** Directory containing one or more .csv files
   * configuring an Image Collection.
   */
  File tsvDir


  /** Constructor with String for directory name.
   * @param dirName Name of directory containing .tsv files.
   * @throws Exception if a File cannot be made from dirName.
   */
  TsvCollections(String dirName) 
  throws Exception {
    this.tsvDir = new File(dirName)
  }


  /** Constructor with File for directory.
   * @param dir Directory containing .csv files.
   */
  TsvCollections(File dir)  {
    this.tsvDir = dir
  }

  /** Returns an ArrayList of ImageCollection objects.
   * @throws Exception if input line cannot be parsed.
   */
  ArrayList getCollectionConfigs() 
  throws Exception {
    ArrayList imageCollections = []

    this.tsvDir.eachFileMatch(~/.*.tsv/) { file ->  
      Integer num = 0
      file.eachLine { ln ->
	num++;
	ArrayList quintuplet = ln.split(/\t/)
	if (quintuplet.size() != 5) {
	  throw new Exception("TsvCollections:  in file ${file}, ${quintuplet.size()} columns in line ${bum}")
	}
	imageCollections.add(new ImageCollection(quintuplet))
      }
    }
    return imageCollections
  }


}

