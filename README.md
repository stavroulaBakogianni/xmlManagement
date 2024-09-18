# XML Management Application

## Overview

The **XML Management Application** is a Java-based project designed to facilitate the conversion of text files into XML format, as well as the manipulation and validation of XML documents. The system allows for generating and reading structured XML files, extracting specific XML fragments, calculating statistics, and validating XML files against an XSD schema.

## Features

- **Convert Text Files to XML**: Parse plain text files and convert them into structured XML documents with chapters, paragraphs, and sentences.
- **Extract XML Fragments**: Extract specific chapters or fragments from an XML document.
- **Calculate Statistics**: Generate statistics such as the number of paragraphs, sentences, words, and distinct words in both XML and text formats.
- **Validate XML Against XSD**: Ensure XML files conform to a defined schema (XSD) through JAXB unmarshalling.
- **Generate XSD from Java Classes**: Automatically generate XSD schema from JAXB-annotated Java classes.

## Technologies Used

- **Java 21**: The core language for development.
- **JAXB**: Java Architecture for XML Binding, used for marshalling/unmarshalling XML documents.
- **STaX**: Streaming API for XML, used for reading and writing XML documents.
- **Lombok**: Reduces boilerplate code by auto-generating getters, setters, and logging functionality.
- **Maven**: Used for dependency management and building the project.

## Setup and Installation

### Prerequisites

- Java 21 or higher installed
- Maven installed

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/stavroulaBakogianni/xmlManagement.git
   cd xml-management-application

2. **Build the project**:
   ```bash
    mvn clean install

3. **Run the application**:
    You can use the main classes in the project as a starting point to perform XML manipulations.
