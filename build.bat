JAVA -Xms64m -Xmx1024m -jar input-cache/org.hl7.fhir.publisher.jar -ig ig.ini -destination docs
rmdir /q /s docs
del output\*.md
rename output docs