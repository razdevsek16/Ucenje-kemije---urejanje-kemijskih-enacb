
all:
	cd ..; $(MAKE)

parser: ChemicalParser.java

ChemicalParser.java: Chemical.g
	java -cp ../lib/antlr-3.3-complete.jar org.antlr.Tool Chemical.g
