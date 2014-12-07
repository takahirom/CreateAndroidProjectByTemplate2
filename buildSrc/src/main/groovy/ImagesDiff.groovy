class ImagesDiff{
    def diff(directory1,directory2){
        new File(directory1).eachFileRecurse (FileType.FILES) { file ->
            println file
        }

        new File(directory2)
    }
}