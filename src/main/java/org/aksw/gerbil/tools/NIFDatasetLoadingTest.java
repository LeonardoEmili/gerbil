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
package org.aksw.gerbil.tools;

import org.aksw.gerbil.dataset.impl.nif.FileBasedNIFDataset;
import org.aksw.gerbil.exceptions.GerbilException;
import org.apache.jena.riot.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIFDatasetLoadingTest {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(NIFDatasetLoadingTest.class);

    public static void main(String[] args) {
	if (args.length != 1) {
	    LOGGER.error("Wrong usage. Need exactly one single argument. Correct usage: 'NIFDatasetLoadingTest <path-to-nif-file>'.");
	    return;
	}
	LOGGER.info("Starting loading of given test dataset \"" + args[0] + "\"...");
	FileBasedNIFDataset dataset = new FileBasedNIFDataset(args[0],
		"Test dataset", Lang.TTL);
	try {
	    dataset.init();
	} catch (GerbilException e) {
	    LOGGER.error("Got an exception while trying to load the dataset.",
		    e);
	}
	LOGGER.info("Finished loading of given test dataset.");
    }
}