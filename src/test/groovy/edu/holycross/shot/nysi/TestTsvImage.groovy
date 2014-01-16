package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn



import static org.junit.Assert.*
import org.junit.Test

class TestTsvImage extends GroovyTestCase {

    String collSource = "testdata/collections"
    String imgSource = "testdata/images"


    @Test void testImgTsvFile() {
        ImgTurtleizer ittl = new ImgTurtleizer()
        ittl.setTsvData(imgSource)
        ittl.setTsvInventory (collSource)
        def imgData =  ittl.imageSrc.getImageData()

        // one collection, with 486 images:
        def expectedNumImgs = 486
        assert imgData.size() == expectedNumImgs
        assert ittl.collectionSrc.getCollectionsData().size() == 2
    }

}
