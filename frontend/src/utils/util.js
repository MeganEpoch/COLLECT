//本地文件如何转成 base64
export function uploadImgToBase64 (file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader()
        reader.readAsDataURL(file)
        reader.onload = function () { // 图片转base64完成后返回reader对象
            resolve(reader)
        }
        reader.onerror = reject
    })
}