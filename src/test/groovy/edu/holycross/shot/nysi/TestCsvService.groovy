package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn

import au.com.bytecode.opencsv.CSVReader

import static org.junit.Assert.*
import org.junit.Test

class TestCsvService extends GroovyTestCase {

  String collSource = "testdata/collections"

  @Test void testConstructor() {
    CsvCollections imgSvc = new CsvCollections(collSource)
    assert imgSvc
    
    def collList = imgSvc.getCollectionConfigs()
    assert collList.size() == 1
    assert collList[0] instanceof edu.holycross.shot.nysi.ImageCollection
  }
}
