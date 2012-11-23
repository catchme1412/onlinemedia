package com.raj.media;

import java.io.IOException;
import java.net.URL;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Category;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.parser.ParseException;
import org.apache.abdera.parser.Parser;

public class YouTubeMedia {

	public static void main(String[] args) throws ParseException, IOException {
		// String url =
		// "http://gdata.youtube.com/feeds/api/videos?q=malalayalam";
		Abdera abdera = new Abdera();

		Parser parser = abdera.getParser();

		URL url = new URL("http://gdata.youtube.com/feeds/api/videos?q=malalayalam+movies");
		Document<Feed> doc = parser.parse(url.openStream(), url.toString());
		Feed feed = doc.getRoot();
		System.out.println(feed.getTitle());
		System.out.println(feed.getId());
		System.out.println("LLLLLLL" + feed.getUpdatedString());
		for (Entry entry : feed.getEntries()) {
			parseEntry(entry);
			// for (Link l : entry.getLinks()) {
			// System.out.println("\t\t" + l);
			// }
		}
		System.out.println(feed.getAuthor());
	}

	private static Media parseEntry(Entry entry) {
		if (entry.getTitle().toLowerCase().contains("full movie") && entry.getTitle().toLowerCase().contains("malayalam")) {
			System.out.println(entry.getCategories());
			System.out.println("IDDDDDDDDD:" + entry.getId());
			System.out.println(entry.getTitle());
			System.out.println(entry.getEdited());
			for (Category cat : entry.getCategories()) {
				if (!cat.getTerm().startsWith("http")) {
					System.out.println(cat.getTerm());
				}
			}
		}
		return null;
	}
}
