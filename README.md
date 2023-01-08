## evanova-app-translation

#### This repository is for [Evanova Android](https://play.google.com/store/apps/details?id=com.tlabs.android.evanova) users who wish to enhance the translations of this wonderful mobile application for Eve Online.

If you wish to contribute to the supported languages, you can either edit the XML files for your language from GitHub, or clone this repository. Make sure to create pull requests as necessary.

### What do I need?

  * A few hours of your time. The goal is not to translate everything but to make sure that terms for each language are proper.
  * To be proficient in English and Eve Online's specific terms, preferably with your native language being the one you intend to submit translations for.
  * A basic understanding of XML files. They are text files that can be edited by any text editor you may have on your computer and you will need to update only parts of them.  
  * A bit of GitHub experience, which you may already have if you're reading this.

### What to look for?

This repository contains directories with XML files for each of the supported languages in Evanova. 
All translations are based on the files found in 

 * values: English - which is also used as a reference for other languages.
 
 
The following languages need reviewing:
  
  * Japanese (src/main/res/values-ja)
  * Spanish (src/main/res/values-es)

Feel free to submit corrections to the other language, should you find any mistake or have a better alternative.
  
You can edit any of the files for your language and submit your changes with a pull request. Only the content of entries in XML files need to be reviewed, with some care regarding what to change:

#### string
  
The values within the `<string>` elements are to be reviewed, the `"name"` part must not be changed:
  
```
<string name="app_add">Add</string>

<string name="app_add">Hinzufügen</string>

<string name="app_add">Ajouter</string>

<string name="app_add">добавлять</string>

<string name="app_add">加</string>

<string name="app_add">더하다</string>

```

#### string-array

If you encounter a `<string-array>` the idea is the same with the `item` list it contains, with a little difference: you should ignore it if it says `"translatable=false"`.

```
 <string-array name="activity_skills_attributes_map" translatable="true">
    <item>Intelligence</item>
    <item>Perception</item>
    <item>Charisma</item>
    <item>Willpower</item>
    <item>Memory</item>
  </string-array>
```

#### plurals

This one is a little particular: you should translate the item content depending on the `quantity` to match the specifities of your language regarding plurals:

```
    <plurals name="activity_assets_locations_group">
        <item quantity="zero">No item</item>
        <item quantity="one">%d item</item>
        <item quantity="other">%d items</item>
    </plurals>   
```    

#### Handling formatted strings

You might have noticed in the examples above that some strings contain code like `%d` or `1%$s` or similar patterns. Those codes will contain strings provided by the application and should not be changed, although they can be moved around. The first number (after `%`)  indicates the position of the string, and the `$` indicates if it should be a string (`s`) or an integer value (`d`) or other number type. 

eg: the ISK is provided with `%1$s` in this sentence:

```
  <string name="activity_contracts_reward">Награда: %1$s ISK</string>
```


### Many Thanks for your help!

Please make sure to stay in touch by writing to evanova dot mobile at gmail.com or in-game to "Evanova Android".
