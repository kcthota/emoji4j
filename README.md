emoji4j
=============


[![Build Status](https://img.shields.io/travis/kcthota/emoji4j/master.svg)](https://travis-ci.org/kcthota/emoji4j)
[![Coverage Status](https://img.shields.io/coveralls/kcthota/emoji4j/master.svg)](https://coveralls.io/r/kcthota/emoji4j?branch=master)
[![Apache 2.0](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://img.shields.io/badge/Maven-Central-blue.svg)](https://maven-badges.herokuapp.com/maven-central/com.kcthota/emoji4j)
[![Java Doc](https://img.shields.io/badge/JavaDoc-5.0-brightgreen.svg)](https://www.javadoc.io/doc/com.kcthota/emoji4j)


[WARNING]: This project is not actively being maintainted. You may use [vdurmont/emoji-java](https://github.com/vdurmont/emoji-java) as an alternative.

Java library to convert short codes, html entities to emojis and vice-versa. Also supports parsing emoticons, surrogate html entities.

Inspired by [vdurmont/emoji-java](https://github.com/vdurmont/emoji-java), emoji4j adds more goodies and helpers to deal with emojis. The emoji data is based on the database from [github/gemoji](https://github.com/github/gemoji) and ASCII emoticons data from [wooorm/emoticon](https://github.com/wooorm/emoticon).

# Usage

Stable:

```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>emoji4j</artifactId>
	<version>6.0</version>
</dependency>
```

<!--
Latest Snapshot:
```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>emoji4j</artifactId>
	<version>3.0-SNAPSHOT</version>
</dependency>
```
-->

# Examples:

## getEmoji

Get emoji by unicode, short code, decimal or hexadecimal html entity

```
Emoji emoji = EmojiUtils.getEmoji("🐭"); //get emoji by unicode character

EmojiUtils.getEmoji("blue_car").getEmoji(); //returns 🚙

EmojiUtils.getEmoji(":blue_car:").getEmoji(); //also returns 🚙

EmojiUtils.getEmoji("&#x1f42d;").getEmoji(); //returns 🐭

EmojiUtils.getEmoji("&#128045;").getEmoji(); //also returns 🐭

EmojiUtils.getEmoji(":)").getEmoji(); //returns 😃

EmojiUtils.getEmoji("&#55357;&#56833;").getEmoji(); //returns 😁

```

## The Emoji Object

Conversion from unicode, short code, hexadecimal and decimal html entities is pretty easy.

```
Emoji emoji = EmojiUtils.getEmoji("🐭");

emoji.getEmoji(); //returns 🐭

emoji.getDecimalHtml(); //returns &#128045;

emoji.getHexHtml(); //return &#x1f42d;

emoji.getAliases(); //returns a collection of aliases. ["mouse"]

```

## isEmoji

Verifies if the passed string is an emoji character

```
EmojiUtils.isEmoji("🐭"); //returns true

EmojiUtils.isEmoji("blue_car"); //returns true

EmojiUtils.isEmoji(":coyote:"); //returns false

EmojiUtils.isEmoji("&#x1f42d;"); //returns true

EmojiUtils.isEmoji("&#128045;"); //returns true

```

## emojify

Emojifies the passed string

```
String text = "A :cat:, :dog: and a :mouse: became friends<3. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.emojify(text); //returns A 🐱, 🐶 and a 🐭 became friends❤️. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.

String text = "A &#128049;, &#x1f436; and a :mouse: became friends. For the :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:."

EmojiUtils.emojify(text); //returns A 🐱, 🐶 and a 🐭 became friends. For the 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.

String text=":):-),:-):-]:-xP=*:*<3:P:p,=-)";

EmojiUtils.emojify(text); //returns 😃😃😅😃😶😝😗😗❤️😛😛😅

```

## htmlify
Converts unicode characters in text to corresponding decimal html entities

```
String text = "A :cat:, :dog: and a :mouse: became friends. For the :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.htmlify(text); //returns A &#128049;, &#128054; and a &#128045; became friends. For the &#128054;'s birthday party, they all had &#127828;s, &#127839;s, &#127850;s and &#127856;.

String text = "A 🐱, 🐶 and a 🐭 became friends. For the 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰."

EmojiUtils.htmlify(text); //also returns A &#128049;, &#128054; and a &#128045; became friends. For the &#128054;'s birthday party, they all had &#127828;s, &#127839;s, &#127850;s and &#127856;.

```

## hexHtmlify

Converts unicode characters in text to corresponding decimal hexadecimal html entities

```
String text = "A :cat:, :dog: and a :mouse: became friends. For the :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.hexHtmlify(text); //returns A &#x1f431;, &#x1f436; and a &#x1f42d; became friends. For the &#x1f436;'s birthday party, they all had &#x1f354;s, &#x1f35f;s, &#x1f36a;s and &#x1f370;.

String text = "A 🐱, 🐶 and a 🐭 became friends. For the 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰."

EmojiUtils.hexHtmlify(text); //returns A &#x1f431;, &#x1f436; and a &#x1f42d; became friends. For the &#x1f436;'s birthday party, they all had &#x1f354;s, &#x1f35f;s, &#x1f36a;s and &#x1f370;.

```

## htmlify as Surrogate Entities

Converts unicode characters in text to corresponding decimal surrogate html entities

```
String text = "😃";

EmojiUtils.htmlify(text, true); //returns &#55357;&#56835;

```

##shortCodify

```
String text = "A 🐱, 🐶 and a 🐭 became friends❤️. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.";

EmojiUtils.shortCodify(text); //returns A :cat:, :dog: and a :mouse: became friends:heart:. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.

text = ":):-),:-):-]:-xP=*:*<3:P:p,=-)";

EmojiUtils.shortCodify(text); //returns :smiley::smiley::sweat_smile::smiley::no_mouth::stuck_out_tongue_closed_eyes::kissing::kissing::heart::stuck_out_tongue::stuck_out_tongue::sweat_smile:

```

## RemoveAllEmojis
Removes unicode emoji characters from the passed string

```
String emojiText = "A 🐱, 🐱 and a 🐭 became friends❤️. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.";

EmojiUtils.removeAllEmojis(emojiText);//"A ,  and a  became friends. For 's birthday party, they all had s, s, s and .

```

## countEmojis

Counts emojis in a String

```
String text = "A &#128049;, &#x1f436;,&nbsp;:coyote: and a :mouse: became friends. For :dog:'s birthday party, they all had 🍔s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.countEmojis(text); //returns 8

```

## Stop words support

Add stop words to filter out certain words from emoji conversion 

```
String text = "http://www.github.com and https://www.google.com are awesome :-).";

EmojiManager.addStopWords("http://", "https://");

EmojiUtils.emojify(userText) //returns http://www.github.com and https://www.google.com are awesome 😃.

```
<!--
# Coming up in 3.0

-->

## License:

Copyright 2016 Krishna Chaitanya Thota (kcthota@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[github/gemoji's](https://github.com/github/gemoji) license:

octocat, squirrel, shipit
Copyright (c) 2013 GitHub Inc. All rights reserved.

bowtie, neckbeard, fu
Copyright (c) 2013 37signals, LLC. All rights reserved.

feelsgood, finnadie, goberserk, godmode, hurtrealbad, rage 1-4, suspect
Copyright (c) 2013 id Software. All rights reserved.

trollface
Copyright (c) 2013 whynne@deviantart. All rights reserved.

All other images
Copyright (c) 2013 Apple Inc. All rights reserved.

[wooorm/emoticon's](https://github.com/wooorm/emoticon) license:

Copyright (c) 2014 Titus Wormer <tituswormer@gmail.com>


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/kcthota/emoji4j/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

