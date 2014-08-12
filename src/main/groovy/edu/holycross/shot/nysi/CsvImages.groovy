package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn

import au.com.bytecode.opencsv.CSVReader

class CsvImages implements ImagesSrc {


    File csvDir

    CsvImages(String dirName) 
    throws Exception {
        this.csvDir = new File(dirName)
    }

    CsvImages(File dir)  {
        this.csvDir = dir
    }


    java.util.ArrayList getImageData() {
        def allTriplets =  []
        this.csvDir.eachFileMatch(~/.*.csv/) { file ->  
            CSVReader reader = new CSVReader(new FileReader(file))

            
            reader.readAll().each { ln ->
                def triplet = []
                ln.each { val ->
                    triplet.add(val)
                }
                allTriplets.add(triplet)
            }
        }
        return allTriplets
    }
}

