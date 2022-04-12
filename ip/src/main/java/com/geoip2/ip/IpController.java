package com.geoip2.ip;

import com.geoip2.ip.util.IpUtil;
import com.geoip2.ip.util.IpUtils;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.*;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/ip")
public class IpController {
    public static void main(String[] args) throws DbMakerConfigException, IOException {
        String dbPath = IpUtils.class.getResource("/db/ip2region.db").getPath();
        if (searcher == null) {
            searcher = new DbSearcher(new DbConfig(), dbPath);

        }
        DataBlock dataBlock = searcher.memorySearch("57.88.191.255");

        String region = dataBlock.getRegion();
        System.out.println(region);
    }

    public static DbSearcher searcher;

    public static DatabaseReader reader;

    @GetMapping("getIp")
    @ResponseBody
    public String getIp(HttpServletRequest request) throws IOException, GeoIp2Exception, DbMakerConfigException {
        String realIP = IpUtil.getRealIP(request);
//        // A File object pointing to your GeoIP2 or GeoLite2 database
////        File database = new File("/path/to/GeoIP2-City.mmdb");
        if (reader == null) {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:db/GeoLite2-Country.mmdb");
//// This creates the DatabaseReader object. To improve performance, reuse
//// the object across lookups. The object is thread-safe.
            reader = new DatabaseReader.Builder(resource.getInputStream()).build();
        }

////
        InetAddress ipAddress = InetAddress.getByName("1.0.0.0");
//        InetAddress ipAddress = InetAddress.getByName("111.249.136.183");
//        long l = IPUtil.ip2long("111.249.136.183");
//
//// Replace "city" with the appropriate method for your database, e.g.,
//// "country".
        CountryResponse response = reader.country(ipAddress);
        Country country = response.getCountry();
        System.out.print(country.getIsoCode() + "|");            // 'US'
        System.out.print(country.getName() + "|");               // 'United States'
        System.out.print(country.getGeoNameId() + "|");               // 'United States'
        System.out.println(country.getNames().get("zh-CN") + "|"); // '美国'
//        CityResponse response = reader.city(ipAddress);
//        Country country = response.getCountry();
//        System.out.print(country.getIsoCode() + "|");            // 'US'
//        System.out.print(country.getName() + "|");               // 'United States'
//        System.out.print(country.getNames().get("zh-CN") + "|"); // '美国'
//        Subdivision subdivision = response.getMostSpecificSubdivision();
//        System.out.print(subdivision.getName() + "|");    // 'Minnesota'
//        System.out.print(subdivision.getIsoCode() + "|"); // 'MN'
//
//        City city = response.getCity();
//        System.out.print(city.getName() + "|"); // 'Minneapolis'
//
//        Postal postal = response.getPostal();
//        System.out.print(postal.getCode() + "|"); // '55455'
//
//        Location location = response.getLocation();
//        System.out.print(location.getLatitude() + "|");  // 44.9733
//        System.out.print(location.getLongitude() + "|"); // -93.2323

//        System.out.println(IpUtils.getMemoryAddress("1.0.0.0"));
//        System.out.println(IpUtils.getMemoryAddress("57.88.191.255"));
//        System.out.println(IpUtils.getMemoryAddress("57.82.47.255"));
//        System.out.println(IpUtils.getMemoryAddress("57.82.47.255"));
//        System.out.println(IpUtils.getMemoryAddress("2.20.127.255"));
//        System.out.println(IpUtils.getMemoryAddress("2.17.194.255"));
//        System.out.println(IpUtils.getMemoryAddress("57.85.31.255"));
//        System.out.println(IpUtils.getMemoryAddress("57.89.31.255"));
//        System.out.println(IpUtils.getMemoryAddress("57.93.79.255"));
//        System.out.println(IpUtils.getMemoryAddress("14.190.63.255"));
//        System.out.println(IpUtils.getMemoryAddress("122.200.61.255"));
//        System.out.println(IpUtils.getMemoryAddress("23.73.127.255"));
//        System.out.println(IpUtils.getMemoryAddress("23.219.155.255"));
//        System.out.println(IpUtils.getMemoryAddress("13.94.127.255"));
//        System.out.println(IpUtils.getMemoryAddress("1.0.31.255"));


        return "";
    }
}
