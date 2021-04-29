package com.ocha.mcdterdekat.model.location;

import java.util.ArrayList;

public class LocationData {
    public static ArrayList<LocationModel> getLocations(){
        ArrayList<LocationModel> locations = new ArrayList<LocationModel>();
        locations.add(
                new LocationModel(
                        1,
                        "McDonald's Ambarukmo",
                        "jl laksda adisucipti, DIY",
                        " hanya mcd",
                        -7.7829023239094175,
                        110.40703353157664,
                        "www.dummy.com/image",
                        1.0
                )
        );
        locations.add(
                new LocationModel(
                        2,
                        "McDonald's Jakal",
                        "jl kaliurang, DIY",
                        " hanya mcd",
                        -7.7625194129543775,
                        110.37972051290228,
                        "www.dummy.com/image",
                        1.0
                )
        );
        locations.add(
                new LocationModel(
                        3,
                        "McDonald's Jombor",
                        "jl siliwangi, DIY",
                        " hanya mcd",
                        -7.748478910938432,
                        110.36178573037928,
                        "www.dummy.com/image",
                        1.0
                )
        );
        locations.add(
                new LocationModel(
                        4,
                        "McDonald's Sudirman",
                        "jl jen sudirman, DIY",
                        " hanya mcd",
                        -7.783244628837519,
                        110.37166789521126,
                        "www.dummy.com/image",
                        1.0
                )
        );
        locations.add(
                new LocationModel(
                        5,
                        "McDonald's Sultan Agung",
                        "jl bintaran wetan",
                        " hanya mcd",
                        -7.801927454111592,
                        110.37464631235102,
                        "www.dummy.com/image",
                        1.0
                )
        );
        return locations;
    }
}
