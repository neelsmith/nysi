package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn

import au.com.bytecode.opencsv.CSVReader

import static org.junit.Assert.*
import org.junit.Test

class TestCols1 extends GroovyTestCase {

  String collSource = "testdata/collections-numcols"

  @Test void testCols1() {
    ImgTurtleizer ittl = new ImgTurtleizer(collSource)
    assert shouldFail {
      String testttl = ittl.ttlize() 
    }
  }
}
