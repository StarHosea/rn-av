import { NativeModules } from 'react-native';
import {IAv} from "./av-def";

const { TwAv: NativeAv } = NativeModules;

export const Av:IAv = NativeAv;