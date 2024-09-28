
RN ffmpeg based av converter.

- supported platform: Android

## Installation
```
npm install @starhosea/rn-av
```

## How to Use


```tsx
import { Av } from '@starhosea/rn-av';

const source = "/storage/sdcard/0/Music/a.wav";
const target = "/storage/sdcard/0/Music/a-converted.mp3";

// enable log
Av.enableLog((message) => {
    console.log('AV:' + message.message);
});
// convert file from wav to mp3
const res = await Av.convertFile(source, target);

// cancel convert
Av.cancelConvert();
```
