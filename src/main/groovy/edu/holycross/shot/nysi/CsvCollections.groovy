package edu.holycross.shot.nysi

import edu.harvard.chs.cite.Cite2Urn
import edu.harvard.chs.cite.CiteUrn

import au.com.bytecode.opencsv.CSVReader


/** Implementation of the ImageService interface
 * for collections documented in .csv files.
 */
class CsvCollections implements ImageService {

  /** Directory containing one or more .csv files
   * configuring an Image Collection.
   */
  File csvDir


  /** Constructor with String for directory name.
   * @param dirName Name of directory containing .csv files.
   * @throws Exception if a File cannot be made from dirName.
   */
  CsvCollections(String dirName)
  throws Exception {
    this.csvDir = new File(dirName)
  }


  /** Constructor with File for directory.
   * @param dir Directory containing .csv files.
   */
  CsvCollections(File dir)  {
    this.csvDir = dir
  }



	/** Returns an ArrayList of ImageCollection objects.
	* @throws Exception if input line cannot be parsed.
	*/
	ArrayList getCollectionConfigs()
	throws Exception {
		ArrayList imageCollections =  []

		this.csvDir.eachFileMatch(~/.*.csv/) { file ->
			CSVReader reader = new CSVReader(new FileReader(file))
			Integer num = 0
			reader.readAll().each { ln ->
				num++;
				if (ln.size() != 5) {
					throw new Exception("CsvCollections: in file ${file}, ${ln.size()} columns in line ${num}")

					} else {
						def quintuplet = []
						ln.each { val ->
							quintuplet.add(val)
						}
						imageCollections.add(new ImageCollection(quintuplet))
					}
				}
			}
			return imageCollections
		}
	}
