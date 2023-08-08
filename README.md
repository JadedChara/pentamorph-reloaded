

# Pentamorph
## About
Pentamorph is somewhere between a passion project and something for fun. The actual project itself, though? A Transformers mod for Quilt. I enjoyed messing around with the Forge ones, but due to Forge's "issues" and, well, jankiness of some of the code, I can't really justify trying to work with it anymore. Likewise, Fabric's got the same problem in terms of the community, and has sections of the framework the devs refuse to patch.

What will this mod include? Frankly, I don't know, yet! I've got a lot of plans, but we'll se how everything pans out. The core NPC mechanic for this project is going to be focused on my own take on the Quintessons. Somewhat parallel to that will be the Insecticons.

Players themselves will be able to play as Cybertronian characters eventually, with the player's hitbox, scale, and model all being modified, as well as special features for each.

Other technical stuff will be weapons and gear based off some of what's been offered for the games, as well as some trickier bits of tech.

Stay tuned, I suppose!

## Roadmap
Pentamorph's got a hefty list of planned things, and this list may change, but here's the beginning breakdown of it:
* [ ] Quintessons
    * [ ] Entities
        * [ ] Larva
        * [ ] Juror
        * [ ] Bailiff
        * [ ] TBD
    * [ ] Structures
        * [ ] Small Nest
        * [ ] Medium Nest
        * [ ] Large Nest
        * [ ] Laboratory
        * [ ] Cyberforming Core
        * [ ] Court
        * [ ] TBD
* [ ] Blocks
    * [x] Nest Pulp
    * [x] Calcified Nest Pulp
    * [ ] TBD
* [ ] Insecticons
    * [ ] Insectiqueen
    * [ ] Insecticon Brutes
    * [ ] Insecticon Drones
    * [ ] Insecticon Clones
    * [ ] TBD
* [ ] Player System
    * [ ] Krok
    * [ ] TBD
* [ ] TBD


----
----




# Quilt Template Mod

The official Quilt template mod. You can use it as a template for your own mods!

## Usage

In order to use this mod as a template:

1. Create a new repository from this template with `Use this template`
2. Clone the recently-created repo on your PC
3. Make the necessary changes in order to make it yours:
    - Update `gradle.properties` in order to use your Maven group and mod ID
        - If you don't know which Maven group to use, and you are planning to host the mod's source code on GitHub, use `io.github.<Your_Username_Here>`
- Update `quilt.mod.json` in order to reflect your mod's metadata
    - If you are planning to include (jar-in-jar) a mod, don't forget to declare its dependency on it!
    - The icon provided here is a placeholder one. If you aren't able to replace it yet, you can delete it and remove the "icon" property
    - Create a LICENSE file for this mod! If you don't know which license to use, check out [here](https://choosealicense.com/).
        - If you use `LICENSE.md`, don't forget to update the buildscript in order to use that file name!
        - In `quilt.mod.json`, don't forget to put the license's [SPDX identifier](https://spdx.org/licenses/) under the `"license"` property in `"metadata"`.
        - The GPLv3 and AGPLv3 are not valid mod licenses, so you can use almost any license except for those.
    - Update the Java sub-directory structure so it reflects your Maven group
    - If the dependencies on `gradle/libs.versions.toml` isn't up-to-date, feel free to update them! The [linked utility](https://lambdaurora.dev/tools/import_quilt.html) should help you in this easy and quick process.
4. The mod is now ready to be worked on!

## License

This template on the QuiltMC GitHub is licensed under the [Creative Common Zero v1.0 license](./LICENSE-TEMPLATE.md).

Mods created with this template are not automatically licensed under the CC0, and are not required to give any kind of credit back to QuiltMC for this template.
