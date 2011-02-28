package it.unibz.model;

import org.dom4j.Element;

public interface TweetFetcher {
    public String tweet(Element element) throws TweetException;
}

