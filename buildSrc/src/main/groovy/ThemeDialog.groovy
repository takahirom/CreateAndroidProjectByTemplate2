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
            dialog(modal: true, // Otherwise the build will continue running before you closed the dialog
                    title: 'Enter Theme setting', // Dialog title
                    alwaysOnTop: true, // pretty much what the name says
                    resizable: false, // Don't allow the user to resize the dialog
                    locationRelativeTo: null, // Place dialog in center of the screen
                    pack: true, // We need to pack the dialog (so it will take the size of it's children
                    show: true // Let's show it
            ) {
                vbox { // Put everything below each other
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
                        dispose(); // Close dialog
                    })
                }
            }
        }
    }
}
