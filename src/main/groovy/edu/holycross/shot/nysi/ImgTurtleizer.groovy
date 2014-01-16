package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CiteUrn
 
/** Class for working with a variety of data sources, and
* generting RDF statements.
*/
class ImgTurtleizer {

    static String prefix = "@prefix hmt:        <http://www.homermultitext.org/hmt/rdf/> .\n@prefix cite:        <http://www.homermultitext.org/cite/rdf/> .\n@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n"

    ImagesSrc imageSrc
    File inventoryDir

    CollectionsSrc collectionSrc
    File dataDir


    /** 
    */
    ImgTurtleizer(String collDirName, String imgDirName) 
    throws Exception {
        this.inventoryDir = new File (collDirName)
        this.dataDir = new File(imgDirName)
    }


    String ttlizeCollections() 
    throws Exception {
        StringBuffer reply = new StringBuffer()
        this.collectionSrc.getCollectionsData().each { triple ->
            if (triple[0].size() > 2) {
                try {
                    CiteUrn urn = new CiteUrn(triple[0])
                    reply.append("<${urn}> rdf:type cite:ImageArchive .\n")
                    reply.append("<${urn}> rdf:label " + '"' + triple[1] + '" .\n')
                    reply.append("<${urn}> hmt:path " + '"' + triple[2] + '" .\n')

                } catch (Exception e) {
                    System.err.println "ImgTurtleizer: FAILED TO PARSE record for ${triple[0]} with length ${triple[0].size()}"
                }
            }
        }
        return reply.toString()
    }

    String ttlizeImages() {
        StringBuffer reply = new StringBuffer()
        this.imageSrc.getImageData().each { triple ->
            try {
                CiteUrn urn = new CiteUrn(triple[0])
                reply.append("<${urn}> cite:belongsTo <urn:cite:${urn.getNs()}:${urn.getCollection()}> .\n")
                reply.append("<urn:cite:${urn.getNs()}:${urn.getCollection()}> cite:possesses <${urn}> .\n")

                reply.append("<${urn}> rdf:label " + '"' + triple[1] + '" .\n')
                reply.append("<${urn}> cite:license " + '"' + triple[2] + '" .\n')
                reply.append("<${urn}> cite:ordered " + '"false" .\n')

            } catch (Exception e) {
                System.err.println "ImgTurtleizer: failed on urn value ${triple[0]}"
            }
        }
        return reply.toString()
    }

    String ttlize() {
        return ttlize(true)
    }


    void ttl(File outFile) {
        ttl(outFile, false)
    }

    void ttl(File outFile, boolean includePrefix) {
        outFile.append(ttlize(includePrefix), "UTF-8")
    }

    String ttlize(boolean includePrefix) {
        StringBuffer reply = new StringBuffer()
        if (includePrefix) {
            reply.append(prefix)
        }

        this.collectionSrc = new TsvCollections(this.inventoryDir)
        reply.append(ttlizeCollections())
        this.collectionSrc = new CsvCollections(this.inventoryDir)
        reply.append(ttlizeCollections())

        this.imageSrc = new CsvImages(this.dataDir)
        reply.append(ttlizeImages())
        this.imageSrc = new TsvImages(this.dataDir)
        reply.append(ttlizeImages())

        return reply.toString()
    }
}
