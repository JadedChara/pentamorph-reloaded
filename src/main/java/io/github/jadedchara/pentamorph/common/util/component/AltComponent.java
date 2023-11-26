package io.github.jadedchara.pentamorph.common.util.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;

public interface AltComponent extends AutoSyncedComponent, ServerTickingComponent {
	ComponentKey<AltComponent> ALT = ComponentRegistry.getOrCreate(new Identifier("pentamorph","alt"), AltComponent.class);

}
