package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn

import au.com.bytecode.opencsv.CSVReader

import static org.junit.Assert.*
import org.junit.Test

class TestCsvImage extends GroovyTestCase {

    String collSource = "testdata/collections"
    String imgSource = "testdata/images"

 

    @Test void testImgCsvFile() {
        ImgTurtleizer ittl = new ImgTurtleizer(collSource, imgSource)
        System.err.println "Got here"
        def imgData =  ittl.imageSrc.getImageData()

        // one collection, with 486 images:
        def expectedNumImgs = 486
        assert imgData.size() == expectedNumImgs
        assert ittl.collectionSrc.getCollectionsData().size() == 2
    }

}
