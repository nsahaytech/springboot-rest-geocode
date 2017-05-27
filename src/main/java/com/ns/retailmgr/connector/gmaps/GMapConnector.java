package com.ns.retailmgr.connector.gmaps;

import java.util.Map;

import com.ns.retailmgr.connector.gmaps.model.GeoCodeLocInfo;

public interface GMapConnector {
	
	Map<String, String> getLngLatByAddress(final Long address) throws RuntimeException;
	
	GeoCodeLocInfo getNearestShopDetails(final String latlngParam) throws RuntimeException;

}
