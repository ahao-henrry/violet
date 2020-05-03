package com.ahao.violet.vo;

/**
 * @author ahao
 * @since 2020/5/3 下午2:49
 */
public class TextVO extends BaseVO {

    private static final long serialVersionUID = -4998997354075889828L;

    private String id;
    private String fileId;
    private FileVO fileVO;
    private String textInfo;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FileVO getFileVO() {
        return fileVO;
    }

    public void setFileVO(FileVO fileVO) {
        this.fileVO = fileVO;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }
}
