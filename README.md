# "Now you see it":  a build system for CITE Image services #

`nysi` generates RDF statements from a collection CITE Images using the RDF vocabulary expected by the `sparqlcts` implementation of CITE Image Services.

Initially, `nysi` supports data in locally stored `csv` files.  It could easily expand to include Google Fusion Tables and local `tsv` files as data sources.


## Data sources ##


*Image collections* should be documented with three properties:

1. A unique CITE URN
2. A labelling description
3. A path where pyramidal source files are stored.

*Individual images* should be documented with three properties:

1. A unique CITE URN
2. A caption
3. A statement about licensing and rights for reuse


