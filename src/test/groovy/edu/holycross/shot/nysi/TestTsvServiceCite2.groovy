package edu.holycross.shot.nysi

import static org.junit.Assert.*
import org.junit.Test

class TestTsvServiceCite2 extends GroovyTestCase {

  String collSource = "testdata/collections"

  @Test void testConstructor() {
    TsvCollections imgSvc = new TsvCollections(collSource)
    assert imgSvc

    def collList = imgSvc.getCollectionConfigs()
    assert collList.size() == 2
    assert collList[0] instanceof edu.holycross.shot.nysi.ImageCollection
    assert collList[1] instanceof edu.holycross.shot.nysi.ImageCollection
  }
}
