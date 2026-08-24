webFXTreeConfig.folderIcon    = "images/tree/folder.png";
webFXTreeConfig.openFolderIcon  = "images/tree/openfolder.png";
webFXTreeConfig.BPMmodel = 'images/16.process.png';
webFXTreeConfig.BPMfolder = 'images/process_project.png';
webFXTreeConfig.BPMprocess = 'images/adorned_folder_process.png';


webFXTreeConfig.rootIcon    = "images/tree/folder.png";
webFXTreeConfig.openRootIcon  = "images/tree/openfolder.png";
webFXTreeConfig.lMinusIcon    = "images/tree/Lminus.png";
webFXTreeConfig.lPlusIcon   = "images/tree/Lplus.png";
webFXTreeConfig.tMinusIcon    = "images/tree/Tminus.png";
webFXTreeConfig.tPlusIcon   = "images/tree/Tplus.png";
webFXTreeConfig.iIcon     = "images/tree/I.png";
webFXTreeConfig.lIcon     = "images/tree/L.png";
webFXTreeConfig.tIcon     = "images/tree/T.png";
webFXTreeConfig.blankIcon = 'images/tree/blank.png';

// Return xtree node representing server parent
function getServerTree(name, serverList, serverTreeList) {
	for (s in serverList) {
		if (serverList[s] == name)
			return (serverTreeList[s]);
	}
	return null;
}
