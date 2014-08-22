package edu.holycross.shot.nysi

import static org.junit.Assert.*
import org.junit.Test

class TestTsvService extends GroovyTestCase {

  String collSource = "testdata/collections"

  @Test void testConstructor() {
    TsvCollections imgSvc = new TsvCollections(collSource)
    assert imgSvc
    
    def collList = imgSvc.getCollectionConfigs()
    assert collList.size() == 1
    assert collList[0] instanceof edu.holycross.shot.nysi.ImageCollection
  }
}
