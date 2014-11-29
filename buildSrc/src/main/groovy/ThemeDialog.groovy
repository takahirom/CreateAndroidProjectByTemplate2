import javax.swing.filechooser.FileFilter
import javax.swing.JFileChooser
import groovy.swing.SwingBuilder
import java.awt.*
class ThemeDialog{
    def appName
    def directoryName
    def packageName
    def themePath
    def color
    def show() {
        def sBuilder = new SwingBuilder()
        sBuilder.edt {
            // Dialog settings
            dialog(modal: true,
                    title: 'Enter Theme setting', // Dialog title
                    alwaysOnTop: true,
                    resizable: false,
                    locationRelativeTo: null,
                    pack: true,
                    show: true
            ) {
                // Vertical layout
                vbox {
                    label(text: "Please enter theme name:")
                    def nameInput = textField(text: "App Name テスト")
                    label(text: "Please enter folder name:")
                    def directoryNameInput = textField(text: "test")
                    label(text: "Please enter theme package name:")
                    def packageInput = textField(text: "com.exmpale.test")
                    label(text: "Please enter text color code:")
                    def colorInput = textField(text: "#0000FF")

                    label(text: " path:")
                    def themeImageFolderPathInput
                    hbox {
                        themeImageFolderPathInput = textField(text: "")
                        button = button(defaultButton: true, text: 'OPEN', actionPerformed: {
                            def filter = [
                                    getDescription: { -> "Directory" },
                                    accept        : { file -> file.isDirectory() }
                            ] as FileFilter

                            def builder = new SwingBuilder()
                            def fc = builder.fileChooser(dialogTitle: 'Choose an Directory',
                                    fileSelectionMode: JFileChooser.DIRECTORIES_ONLY,
                                    fileFilter: filter)
                            if (fc.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
                                themeImageFolderPathInput.text = fc.selectedFile.absolutePath
                            }
                        })
                    }




                    button(defaultButton: true, text: 'OK', actionPerformed: {
                        appName = nameInput.text
                        directoryName = directoryNameInput.text
                        packageName = packageInput.text
                        themePath = themeImageFolderPathInput.text
                        color = colorInput.text
                        dispose()
                    })
                }
            }
        }
    }
}
