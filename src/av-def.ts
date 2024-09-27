export interface IAv {
    /**
     *
     * @param sourceUri
     * @param targetUri
     */
    convertFile(sourceUri:string, targetUri: string ):Promise<AvConvertResult>;

    /**
     * 取消转换
     */
    cancelConvert():void;

    /***
     * 启动日志
     */
    enableLog(call: (log:AvLog)=>void);
}


export interface AvConvertResult{
    uri: string;
    size: number;
    name: string;
}

export interface AvLog{
    message: string;
}