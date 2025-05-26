package net.dotevolve.base.data.commons.object;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Getter;

@Component
public class StateCodesMap {

    @Getter
    private final Map<String, String> stateCodeToStateNameMap = new HashMap<>();
    private final Map<String, String> fipsCodeToStateCodeMap = new HashMap<>();

    @PostConstruct
    void populateStateCodeToNameMap() {
        stateCodeToStateNameMap.put("al", "Alabama");
        stateCodeToStateNameMap.put("ak", "Alaska");
        stateCodeToStateNameMap.put("as", "American Samoa"); // territory
        stateCodeToStateNameMap.put("az", "Arizona");
        stateCodeToStateNameMap.put("ar", "Arkansas");
        stateCodeToStateNameMap.put("ca", "California");
        stateCodeToStateNameMap.put("co", "Colorado");
        stateCodeToStateNameMap.put("ct", "Connecticut");
        stateCodeToStateNameMap.put("de", "Delaware");
        stateCodeToStateNameMap.put("dc", "District of Columbia");
        stateCodeToStateNameMap.put("fm", "Federated States of Micronesia"); // freely associated state (fm)
        stateCodeToStateNameMap.put("fl", "Florida");
        stateCodeToStateNameMap.put("ga", "Georgia");
        stateCodeToStateNameMap.put("gu", "Guam"); // Territory
        stateCodeToStateNameMap.put("hi", "Hawaii");
        stateCodeToStateNameMap.put("id", "Idaho");
        stateCodeToStateNameMap.put("il", "Illinois");
        stateCodeToStateNameMap.put("in", "Indiana");
        stateCodeToStateNameMap.put("ia", "Iowa");
        stateCodeToStateNameMap.put("ks", "Kansas");
        stateCodeToStateNameMap.put("ky", "Kentucky");
        stateCodeToStateNameMap.put("la", "Louisiana");
        stateCodeToStateNameMap.put("me", "Maine");
        stateCodeToStateNameMap.put("mh", "Marshall Islands"); // Freely associated state (mh), earlier(ml)
        stateCodeToStateNameMap.put("md", "Maryland");
        stateCodeToStateNameMap.put("ma", "Massachusetts");
        stateCodeToStateNameMap.put("mi", "Michigan");
        stateCodeToStateNameMap.put("mn", "Minnesota");
        stateCodeToStateNameMap.put("ms", "Mississippi");
        stateCodeToStateNameMap.put("mo", "Missouri");
        stateCodeToStateNameMap.put("mt", "Montana");
        stateCodeToStateNameMap.put("ne", "Nebraska");
        stateCodeToStateNameMap.put("nv", "Nevada");
        stateCodeToStateNameMap.put("nh", "New Hampshire");
        stateCodeToStateNameMap.put("nj", "New Jersey");
        stateCodeToStateNameMap.put("nm", "New Mexico");
        stateCodeToStateNameMap.put("ny", "New York");
        stateCodeToStateNameMap.put("nc", "North Carolina");
        stateCodeToStateNameMap.put("nd", "North Dakota");
        stateCodeToStateNameMap.put("mp", "Northern Mariana Islands"); // Commonwealth (mp) before (ni)
        stateCodeToStateNameMap.put("oh", "Ohio");
        stateCodeToStateNameMap.put("ok", "Oklahoma");
        stateCodeToStateNameMap.put("or", "Oregon");
        stateCodeToStateNameMap.put("pw", "Palau"); // Freely associated state (pw)
        stateCodeToStateNameMap.put("pa", "Pennsylvania");
        stateCodeToStateNameMap.put("pr", "Puerto Rico"); // Commonwealth
        stateCodeToStateNameMap.put("ri", "Rhode Island");
        stateCodeToStateNameMap.put("sc", "South Carolina");
        stateCodeToStateNameMap.put("sd", "South Dakota");
        stateCodeToStateNameMap.put("tn", "Tennessee");
        stateCodeToStateNameMap.put("tx", "Texas");
        stateCodeToStateNameMap.put("ut", "Utah");
        stateCodeToStateNameMap.put("vt", "Vermont");
        stateCodeToStateNameMap.put("vi", "Virgin Islands"); // (Territory) US Virgin Islands (vi) before(vs)
        stateCodeToStateNameMap.put("va", "Virginia");
        stateCodeToStateNameMap.put("wa", "Washington");
        stateCodeToStateNameMap.put("wv", "West Virginia");
        stateCodeToStateNameMap.put("wi", "Wisconsin");
        stateCodeToStateNameMap.put("wy", "Wyoming");
        stateCodeToStateNameMap.put("__", "Others");

        // canada states from here
        stateCodeToStateNameMap.put("on", "Ontario");
        stateCodeToStateNameMap.put("qc", "Quebec");
        stateCodeToStateNameMap.put("ns", "Nova Scotia");
        stateCodeToStateNameMap.put("nb", "New Brunswick");
        stateCodeToStateNameMap.put("mb", "Manitoba");
        stateCodeToStateNameMap.put("bc", "British Columbia");
        stateCodeToStateNameMap.put("pe", "Prince Edward Island");
        stateCodeToStateNameMap.put("sk", "Saskatchewan");
        stateCodeToStateNameMap.put("ab", "Alberta");
        stateCodeToStateNameMap.put("nl", "Newfoundland and Labrador");
        stateCodeToStateNameMap.put("nt", "Northwest Territories");
        stateCodeToStateNameMap.put("yt", "Yukon");
        stateCodeToStateNameMap.put("nu", "Nunavut");
    }

    @PostConstruct
    void populateFipsCodeToStateCode() {
        fipsCodeToStateCodeMap.put("1", "AL");
        fipsCodeToStateCodeMap.put("2", "AK");
        fipsCodeToStateCodeMap.put("4", "AZ");
        fipsCodeToStateCodeMap.put("5", "AR");
        fipsCodeToStateCodeMap.put("6", "CA");
        fipsCodeToStateCodeMap.put("8", "CO");
        fipsCodeToStateCodeMap.put("9", "CT");
        fipsCodeToStateCodeMap.put("10", "DE");
        fipsCodeToStateCodeMap.put("12", "FL");
        fipsCodeToStateCodeMap.put("13", "GA");
        fipsCodeToStateCodeMap.put("15", "HI");
        fipsCodeToStateCodeMap.put("16", "ID");
        fipsCodeToStateCodeMap.put("17", "IL");
        fipsCodeToStateCodeMap.put("18", "IN");
        fipsCodeToStateCodeMap.put("19", "IA");
        fipsCodeToStateCodeMap.put("20", "KS");
        fipsCodeToStateCodeMap.put("21", "KY");
        fipsCodeToStateCodeMap.put("22", "LA");
        fipsCodeToStateCodeMap.put("23", "ME");
        fipsCodeToStateCodeMap.put("24", "MD");
        fipsCodeToStateCodeMap.put("25", "MA");
        fipsCodeToStateCodeMap.put("26", "MI");
        fipsCodeToStateCodeMap.put("27", "MN");
        fipsCodeToStateCodeMap.put("28", "MS");
        fipsCodeToStateCodeMap.put("29", "MO");
        fipsCodeToStateCodeMap.put("30", "MT");
        fipsCodeToStateCodeMap.put("31", "NE");
        fipsCodeToStateCodeMap.put("32", "NV");
        fipsCodeToStateCodeMap.put("33", "NH");
        fipsCodeToStateCodeMap.put("34", "NJ");
        fipsCodeToStateCodeMap.put("35", "NM");
        fipsCodeToStateCodeMap.put("36", "NY");
        fipsCodeToStateCodeMap.put("37", "NC");
        fipsCodeToStateCodeMap.put("38", "ND");
        fipsCodeToStateCodeMap.put("39", "OH");
        fipsCodeToStateCodeMap.put("40", "OK");
        fipsCodeToStateCodeMap.put("41", "OR");
        fipsCodeToStateCodeMap.put("42", "PA");
        fipsCodeToStateCodeMap.put("44", "RI");
        fipsCodeToStateCodeMap.put("45", "SC");
        fipsCodeToStateCodeMap.put("46", "SD");
        fipsCodeToStateCodeMap.put("47", "TN");
        fipsCodeToStateCodeMap.put("48", "TX");
        fipsCodeToStateCodeMap.put("49", "UT");
        fipsCodeToStateCodeMap.put("50", "VT");
        fipsCodeToStateCodeMap.put("51", "VA");
        fipsCodeToStateCodeMap.put("53", "WA");
        fipsCodeToStateCodeMap.put("54", "WV");
        fipsCodeToStateCodeMap.put("55", "WI");
        fipsCodeToStateCodeMap.put("56", "WY");
        fipsCodeToStateCodeMap.put("60", "AS");
        fipsCodeToStateCodeMap.put("66", "GU");
        fipsCodeToStateCodeMap.put("69", "MP");
        fipsCodeToStateCodeMap.put("72", "PR");
        fipsCodeToStateCodeMap.put("78", "VI");
    }

    public String getStateNameByCode(String code) {
        if (!stateCodeToStateNameMap.containsKey(code.toLowerCase()))
            code = "__";
        return stateCodeToStateNameMap.get(code.toLowerCase());
    }

    public String getStateCodeByFipsCode(String fipsCode) {
        if (!fipsCodeToStateCodeMap.containsKey(fipsCode)) {
            return "__";
        }
        return fipsCodeToStateCodeMap.get(fipsCode);
    }

    public String getStateCodeByName(String name) {
        String stateCode = null;
        for (Entry<String, String> mapEntry : stateCodeToStateNameMap.entrySet()) {
            if (mapEntry.getValue().compareTo(name) == 0) {
                stateCode = mapEntry.getKey();
                break;
            }
        }
        if (stateCode == null) {
            stateCode = "__";
        }
        return stateCode;
    }

    public boolean isStateCodeValid(String inputStateCode) {
        if (!StringUtils.hasLength(inputStateCode)) {
            return false;
        }
        return stateCodeToStateNameMap.containsKey(inputStateCode.toLowerCase());
    }

}
