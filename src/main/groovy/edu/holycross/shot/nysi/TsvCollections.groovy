package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn

class TsvCollections implements CollectionsSrc {

    File tsvDir

    TsvCollections(String dirName) 
    throws Exception {
        this.tsvDir = new File(dirName)
    }

    TsvCollections(File dir)  {
        this.tsvDir = dir
    }


    java.util.ArrayList getCollectionsData() {
        def allTriplets =  []
        this.tsvDir.eachFileMatch(~/.*.tsv/) { file ->  
            file.eachLine { ln ->
                def triplet = ln.split(/\t/)
                allTriplets.add(triplet)
            }
        }
        return allTriplets
    }
}

