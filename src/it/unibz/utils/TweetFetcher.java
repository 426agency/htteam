package it.unibz.utils;

import it.unibz.exception.TweetException;

import org.dom4j.Element;

public interface TweetFetcher {
    public String tweet(Element element) throws TweetException;
}

