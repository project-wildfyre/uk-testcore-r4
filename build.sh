JAVA -Xms64m -Xmx6g -jar input-cache/org.hl7.fhir.publisher.jar -ig ig.ini -tool jekyll -destination docs
rm -r docs
rm output/*.md
mv output docs
cp package-list.json docs