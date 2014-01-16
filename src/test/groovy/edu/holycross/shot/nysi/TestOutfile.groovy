package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn

import au.com.bytecode.opencsv.CSVReader

import static org.junit.Assert.*
import org.junit.Test

class TestOutfile extends GroovyTestCase {

    String collSource = "testdata/collections"
    String imgSource = "testdata/images"

    File f = new File("testdata/output/imgtest.ttl")

    @Test void testTtl() {
        ImgTurtleizer ittl = new ImgTurtleizer(collSource, imgSource)
        ittl.ttl(f, true)
    }


}
