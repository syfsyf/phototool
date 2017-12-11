package org.syfsyf.phototool.cfg.impl;

import com.thoughtworks.xstream.XStream;
import org.syfsyf.phototool.cfg.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ConfigServiceImpl implements ConfigService {

    private XStream xstream;

    public ConfigServiceImpl() {
        xstream = createXStream();
    }

    protected XStream createXStream() {

        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(new Class[]{Profile.class, Config.class});
        return xstream;
    }

    public XStream getXstream() {
        return xstream;
    }

    @Override
    public Config loadConfig() throws FileNotFoundException {
        Config config = null;
        File file = getConfigFile();
        if (!file.exists()) {
            config = new Config();
            saveConfig(config);
        } else {
            config = (Config) getXstream().fromXML(file);
        }
        return config;
    }

    @Override
    public void saveConfig(Config config) throws FileNotFoundException {
        File file = getConfigFile();
        file.getParentFile().mkdirs();
        getXstream().toXML(config, new FileOutputStream(getConfigFile()));

    }

    @Override
    public Profile loadProfile() throws FileNotFoundException {
        Profile profile = null;
        File file = getProfileFile();
        if (!file.exists()) {
            profile = new Profile();
            saveProfile(profile);
        } else {
            profile = (Profile) getXstream().fromXML(file);
        }
        return profile;
    }

    @Override
    public void saveProfile(Profile profile) throws FileNotFoundException {
        File file = getProfileFile();
        file.getParentFile().mkdirs();
        getXstream().toXML(profile, new FileOutputStream(getProfileFile()));

    }

    private String getPhototoolDir() {
        return System.getProperty("user.home") + "/.phototool2";
    }

    /**
     * Gets the profile file.
     *
     * @return the profile file
     */
    private File getProfileFile() {
        return new File(getPhototoolDir() + "/profile.xml");
    }

    /**
     * Gets the config file.
     *
     * @return the config file
     */
    private File getConfigFile() {
        return new File(getPhototoolDir() + "/config.xml");
    }

    @Override
    public GeoPoints loadGeoPoints() throws FileNotFoundException {
        GeoPoints geoPoints = null;
        File file = getGeoPointsFile();
        if (!file.exists()) {
            geoPoints = new GeoPoints();
            GeoPoint point = new GeoPoint();
            point.setName("Gromady");
            point.setLat(50.014714);
            point.setLng(19.96059600000001);
            geoPoints.getGeoPoints().add(point);
            saveGeoPoints(geoPoints);
        } else {
            geoPoints = (GeoPoints) getXstream().fromXML(file);
        }
        return geoPoints;
    }

    @Override
    public void saveGeoPoints(GeoPoints geoPoints) throws FileNotFoundException {

        File file = getGeoPointsFile();
        file.getParentFile().mkdirs();
        getXstream().toXML(geoPoints, new FileOutputStream(getGeoPointsFile()));

    }

    private File getGeoPointsFile() {
        return new File(getPhototoolDir() + "/geopoints.xml");
    }

}
