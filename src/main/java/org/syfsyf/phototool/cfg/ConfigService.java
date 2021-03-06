package org.syfsyf.phototool.cfg;

import java.io.FileNotFoundException;
import java.util.Map;

public interface ConfigService {

    Config loadConfig() throws FileNotFoundException;

    void saveConfig(Config config) throws FileNotFoundException;

    Profile loadProfile() throws FileNotFoundException;

    void saveProfile(Profile profile) throws FileNotFoundException;

    GeoPoints loadGeoPoints() throws FileNotFoundException;

    void saveGeoPoints(GeoPoints geoPoints) throws FileNotFoundException;

    Map<String,String> getGitInfo();

}
