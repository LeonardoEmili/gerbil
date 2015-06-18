/**
 * The MIT License
 * Copyright (c) 2014 Agile Knowledge Engineering and Semantic Web (AKSW) (usbeck@informatik.uni-leipzig.de)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.aksw.gerbil.dataset.impl.nif;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFLanguages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileBasedNIFDataset extends AbstractNIFDataset {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedNIFDataset.class);

    private String filePath;
    private Lang language;

    public FileBasedNIFDataset(String filePath, String name, Lang language) {
        super(name);
        this.filePath = filePath;
        this.language = language;
    }

    public FileBasedNIFDataset(String filePath, String name, String language) {
        super(name);
        this.filePath = filePath;
        this.language = RDFLanguages.nameToLang(language);
        if (this.language == null) {
            this.language = fileExtToLang(filePath);
        }
        if (this.language == null) {
            throw new IllegalArgumentException("Couldn't determine language of dataset.");
        }
    }

    public FileBasedNIFDataset(String filePath, String name) {
        super(name);
        this.filePath = filePath;
        this.language = fileExtToLang(filePath);
        if (this.language == null) {
            throw new IllegalArgumentException("Couldn't determine language of dataset.");
        }
    }

    @Override
    protected InputStream getDataAsInputStream() {
        FileInputStream fin = null;
        try {
            LOGGER.debug("Loading NIF dataset from {}", filePath);
            fin = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            LOGGER.error("Couldn't load NIF dataset from file.", e);
        }
        return fin;
    }

    @Override
    protected Lang getDataLanguage() {
        return language;
    }

    protected static Lang fileExtToLang(String filePath) {
        File file = new File(filePath);
        String ext = file.getName();
        int pos = ext.lastIndexOf('.');
        if (pos < 0) {
            return null;
        }
        return RDFLanguages.fileExtToLang(ext.substring(pos));
    }
}
