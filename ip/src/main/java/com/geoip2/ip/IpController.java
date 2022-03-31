package com.geoip2.ip;

import com.geoip2.ip.util.IpUtil;
import com.geoip2.ip.util.IpUtils;
import com.maxmind.geoip2.DatabaseReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@RestController
@RequestMapping("/ip")
public class IpController {

    @GetMapping("getIp")
    @ResponseBody
    public String getIp(HttpServletRequest request) throws IOException {
        String realIP = IpUtil.getRealIP(request);
//        // A File object pointing to your GeoIP2 or GeoLite2 database
////        File database = new File("/path/to/GeoIP2-City.mmdb");
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:db/GeoLite2-City.mmdb");
//// This creates the DatabaseReader object. To improve performance, reuse
//// the object across lookups. The object is thread-safe.
        DatabaseReader reader = new DatabaseReader.Builder(resource.getInputStream()).build();
//
//        InetAddress ipAddress = InetAddress.getByName("128.101.101.101");
//
//// Replace "city" with the appropriate method for your database, e.g.,
//// "country".
//        CityResponse response = reader.city(ipAddress);
//
//        Country country = response.getCountry();
//        System.out.println(country.getIsoCode());            // 'US'
//        System.out.println(country.getName());               // 'United States'
//        System.out.println(country.getNames().get("zh-CN")); // '美国'
//
//        Subdivision subdivision = response.getMostSpecificSubdivision();
//        System.out.println(subdivision.getName());    // 'Minnesota'
//        System.out.println(subdivision.getIsoCode()); // 'MN'
//
//        City city = response.getCity();
//        System.out.println(city.getName()); // 'Minneapolis'
//
//        Postal postal = response.getPostal();
//        System.out.println(postal.getCode()); // '55455'
//
//        Location location = response.getLocation();
//        System.out.println(location.getLatitude());  // 44.9733
//        System.out.println(location.getLongitude()); // -93.2323


        System.out.println(IpUtils.getAddress("106.35.112.88"));
        System.out.println(IpUtils.getAddress("222.190.125.42"));
        System.out.println(IpUtils.getAddress("206.77.131.86"));
        System.out.println(IpUtils.getAddress("116.37.161.86"));
        System.out.println(IpUtils.getAddress("136.27.231.86"));
        System.out.println(IpUtils.getAddress("127.0.0.1"));
        System.out.println(IpUtils.getAddress("112.17.236.511"));
        return realIP;
    }
}
