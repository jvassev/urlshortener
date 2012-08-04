package jvassev.urlshortener;

public interface UrlShortener {
	public String shorten(String string);
	
	public String expand(String string);
}
