emoji4j
=============

[![Build Status](https://img.shields.io/travis/kcthota/emoji4j/master.svg)](https://travis-ci.org/kcthota/emoji4j)
[![Coverage Status](https://img.shields.io/coveralls/kcthota/emoji4j/master.svg)](https://coveralls.io/r/kcthota/emoji4j?branch=master)
[![Apache 2.0] (https://img.shields.io/github/license/kcthota/emoji4j.svg)] (http://www.apache.org/licenses/LICENSE-2.0)

Java library to convert short codes, html entities to emojis and vice-versa.

Inspired by [vdurmont/emoji-java] (https://github.com/vdurmont/emoji-java), emoji4j adds more goodies and helpers to deal with emojis. The emoji data is based on the database from [github/gemoji] (https://github.com/github/gemoji).

# Usage

```
<dependency>
	<groupId>com.kcthota</groupId>
	<artifactId>emoji4j</artifactId>
	<version>1.0</version>
</dependency>
```

# Examples:

## getEmoji

Get emoji by unicode, short code, decimal or hexadecimal html entity

```
Emoji emoji = EmojiUtils.getEmoji("🐭"); //get emoji by unicode character

EmojiUtils.getEmoji("blue_car").getEmoji(); //returns 🚙

EmojiUtils.getEmoji(":blue_car:").getEmoji(); //also returns 🚙

EmojiUtils.getEmoji("&#x1f42d;").getEmoji(); //returns 🐭

EmojiUtils.getEmoji("&#128045;").getEmoji(); //also returns 🐭

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
String text = "A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.emojify(text); //returns A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.

String text = "A &#128049;, &#x1f436; and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:."

EmojiUtils.emojify(text); //returns A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.

```

## htmlify
Converts unicode characters in text to corresponding decimal html entities

```
String text = "A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.htmlify(text); //returns A &#128049;, &#128054; and a &#128045; became friends. For &#128054;'s birthday party, they all had &#127828;s, &#127839;s, &#127850;s and &#127856;.

String text = "A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰."

EmojiUtils.htmlify(text); //also returns A &#128049;, &#128054; and a &#128045; became friends. For &#128054;'s birthday party, they all had &#127828;s, &#127839;s, &#127850;s and &#127856;.

```

## hexHtmlify

Converts unicode characters in text to corresponding decimal hexadecimal html entities

```
String text = "A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.hexHtmlify(text); //returns A &#x1f431;, &#x1f436; and a &#x1f42d; became friends. For &#x1f436;'s birthday party, they all had &#x1f354;s, &#x1f35f;s, &#x1f36a;s and &#x1f370;.

String text = "A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰."

EmojiUtils.hexHtmlify(text); //returns A &#x1f431;, &#x1f436; and a &#x1f42d; became friends. For &#x1f436;'s birthday party, they all had &#x1f354;s, &#x1f35f;s, &#x1f36a;s and &#x1f370;.

```

## countEmojiTokens

Counts number of short codes, html entities that would be replaced to valid emojis in a string

```
String text = "A &#128049;, &#x1f436;, :coyote: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";

EmojiUtils.countEmojiTokens(text); //returns 8

```

## License:

Copyright 2015 Krishna Chaitanya Thota (kcthota@gmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[github/gemoji's] (https://github.com/github/gemoji) license:

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

Source code:

Copyright (c) 2013 GitHub, Inc.

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
