package io.github.jadedchara.pentamorph.common.util.component;

import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public interface AltComponent extends AutoSyncedComponent, ServerTickingComponent {
	ComponentKey<AltComponent> ALT = ComponentRegistry.getOrCreate(Identifier.of("pentamorph","alt"),
		AltComponent.class);

}
