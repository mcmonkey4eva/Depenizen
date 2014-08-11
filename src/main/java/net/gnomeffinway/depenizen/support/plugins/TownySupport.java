package net.gnomeffinway.depenizen.support.plugins;

import net.aufdemrand.denizen.objects.dLocation;
import net.aufdemrand.denizen.objects.dPlayer;
import net.aufdemrand.denizen.tags.Attribute;
import net.aufdemrand.denizen.utilities.debugging.dB;
import net.gnomeffinway.depenizen.objects.dNation;
import net.gnomeffinway.depenizen.objects.dTown;
import net.gnomeffinway.depenizen.support.Support;
import net.gnomeffinway.depenizen.tags.towny.TownyLocationTags;
import net.gnomeffinway.depenizen.tags.towny.TownyPlayerTags;

public class TownySupport extends Support {

    public TownySupport() {
        registerObjects(dTown.class, dNation.class);
        registerProperty(TownyPlayerTags.class, dPlayer.class);
        registerProperty(TownyLocationTags.class, dLocation.class);
        registerAdditionalTags("town", "nation");
    }

    @Override
    public String additionalTags(Attribute attribute) {

        if (attribute.startsWith("town")) {

            dTown town = null;

            if (attribute.hasContext(1)) {
                if (dTown.matches(attribute.getContext(1))) {
                    town = dTown.valueOf(attribute.getContext(1));
                } else {
                    dB.echoError("Could not match '" + attribute.getContext(1) + "' to a valid town!");
                    return null;
                }
            }

            if (town == null) {
                dB.echoError("Invalid or missing town!");
                return null;
            }

            return town.getAttribute(attribute.fulfill(1));

        }

        else if (attribute.startsWith("nation")) {

            dNation nation = null;

            if (attribute.hasContext(1)) {
                if (dNation.matches(attribute.getContext(1))) {
                    nation = dNation.valueOf(attribute.getContext(1));
                } else {
                    dB.echoError("Could not match '" + attribute.getContext(1) + "' to a valid nation!");
                    return null;
                }
            }

            if (nation == null) {
                dB.echoError("Invalid or missing nation!");
                return null;
            }

            return nation.getAttribute(attribute.fulfill(1));

        }

        return null;

    }

}