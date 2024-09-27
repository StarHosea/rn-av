import {AvConvertResult, AvLog, IAv} from "./av-def";

export const Av:IAv = {
    cancelConvert(): void {
    },
    convertFile(sourceUri: string, targetUri: string): Promise<AvConvertResult> {
        return Promise.resolve({
            uri: '',
            size: 0,
            name: '',
        });
    },
    enableLog(call: (log: AvLog) => void) {
    }

};