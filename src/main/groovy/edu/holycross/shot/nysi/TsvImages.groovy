package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn


class TsvImages implements ImagesSrc {

    File tsvDir

    TsvImages(String dirName) 
    throws Exception {
        this.tsvDir = new File(dirName)
    }

    TsvImages(File dir)  {
        this.tsvDir = dir
    }


    java.util.ArrayList getImageData() {
        def allTriplets =  []
        this.tsvDir.eachFileMatch(~/.*.tsv/) { file ->  
            file.eachLine {  ln ->
                def triplet = ln.split(/\t/)
                allTriplets.add(triplet)
            }
        }
        return allTriplets
    }
}

