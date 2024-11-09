package nc.tunilog.magasinMS.controllers;

import nc.tunilog.magasinMS.models.Magasin;
import nc.tunilog.magasinMS.services.MagasinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("magasins")
public class MagasinController {
    @Autowired
    private MagasinService magasinService;

    @GetMapping()
    public ResponseEntity<Object> allMagasin(){
        List<Magasin> magasins = magasinService.allMagasins();
        if(magasins.isEmpty())
            return ResponseEntity
                    .status(404)
                    .body("MN_Info: There is no storage saved in data base! Try to create one.");
        else
            return ResponseEntity
                    .status(200)
                    .body(magasins);

    }
    @PostMapping("/add")
    public ResponseEntity<Object> newMagasin(@RequestBody Magasin magasin) {
        try {
            Magasin savedMagasin = magasinService.newMagasin(magasin);
            return ResponseEntity
                    .status(200)
                    .body(savedMagasin);
        }
        catch (Exception excep){
            return ResponseEntity
                    .status(400)
                    .body("MN_Err error adding new storage: \n" + excep.getMessage());

        }
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateMagasin(@RequestBody Magasin updatedMagasin){
        try{
            return ResponseEntity
                    .status(200)
                    .body(magasinService.updateMagasin(updatedMagasin));
        }
        catch (Exception excep){
            return ResponseEntity
                    .status(400)
                    .body("MN_Err error updating: \n" + excep.getMessage());
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMagasin(@PathVariable("id") int idMagasin){
        try{
            magasinService.delete(idMagasin);
            return ResponseEntity
                    .status(200)
                    .body("MN_Message: Magasin " + idMagasin + " is successufully deleted.");
        }
        catch(Exception excep){
            return ResponseEntity
                    .status(400)
                    .body("MN_Err: " + excep.getMessage());
        }

    }
}
